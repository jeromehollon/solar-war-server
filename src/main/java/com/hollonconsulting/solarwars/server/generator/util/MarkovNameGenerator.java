package com.hollonconsulting.solarwars.server.generator.util;

import com.hollonconsulting.solarwars.server.appconfig.ApplicationContextHolder;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MarkovNameGenerator {
    private static Logger LOGGER = LoggerFactory.getLogger(MarkovNameGenerator.class);

    private static MarkovNameGenerator instance;
    public static MarkovNameGenerator getInstance() {
        if(instance != null){
            return instance;
        }

        //read files
        try {
            Resource resource = ApplicationContextHolder.getContext().getResource("classpath:names.txt");
            InputStream inputStream = resource.getInputStream();
            String[] sources = IOUtils.toString(inputStream, "UTF-8").split(" ");
            instance = new MarkovNameGenerator(sources, 3, 5);
        }catch (IOException e){
            LOGGER.error("Failed to load names file.", e);
        }

        return instance;
    }

    //constructor
    private MarkovNameGenerator(String[] sampleNames, int order, int minLength)
    {
        //fix parameter values
        if (order < 1)
            order = 1;
        if (minLength < 1)
            minLength = 1;

        _order = order;
        _minLength = minLength;

        //split comma delimited lines
        for (String line : sampleNames)
        {
            String[] tokens = line.split(",");
            for (String word : tokens)
            {
                String upper = word.trim().toUpperCase();
                if (upper.length() < order + 1)
                    continue;
                _samples.add(upper);
            }
        }

        //Build chains            
        for (String word : _samples)
        {
            for (int letter = 0; letter < word.length() - order; letter++)
            {
                String token = word.substring(letter, letter + order);
                List<Character> entry = null;
                if (dictionaryContainsKey(_chains, token))
                    entry = _chains.get(token);
                else
                {
                    entry = new ArrayList<>();
                    _chains.put(token, entry);
                }
                entry.add(word.charAt(letter + order));
            }
        }
    }

    //Get the next random name
    public String getName() {
        //get a random token somewhere : middle of sample word                
        String s = "";
        do
        {
            int n = _rnd.nextInt(_samples.size());
            int nameLength = _samples.get(n).length();
            int index = _rnd.nextInt(_samples.get(n).length() - _order);
            s = _samples.get(n).substring(index, index + _order);
            while (s.length() < nameLength)
            {
                String token = s.substring(s.length() - _order, s.length() - _order + _order);
                char c = GetLetter(token);
                if (c != '?')
                    s += GetLetter(token);
                else
                    break;
            }

            if (s.contains(" "))
            {
                String[] tokens = s.split(" ");
                s = "";
                for (int t = 0; t < tokens.length; t++)
                {
                    if (tokens[t] == "")
                        continue;
                    if (tokens[t].length() == 1)
                        tokens[t] = tokens[t].toUpperCase();
                    else
                        tokens[t] = tokens[t].substring(0, 1) + tokens[t].substring(1).toLowerCase();
                    if (s != "")
                        s += " ";
                    s += tokens[t];
                }
            }
            else
                s = s.substring(0, 1) + s.substring(1).toLowerCase();
        }
        while (_used.contains(s) || s.length() < _minLength);
        _used.add(s);
        return s;
    }

    //Reset the used names
    public void Reset()
    {
        _used.clear();
    }

    //private members
    private Dictionary<String, List<Character>> _chains = new Hashtable<>();
    private List<String> _samples = new ArrayList<>();
    private List<String> _used = new ArrayList<>();
    private Random _rnd = new Random();
    private int _order;
    private int _minLength;

    //Get a random letter from the chain
    private Character GetLetter(String token)
    {
        if (!dictionaryContainsKey(_chains, token))
            return '?';
        List<Character> letters = _chains.get(token);
        int n = _rnd.nextInt(letters.size());
        return letters.get(n);
    }

    private boolean dictionaryContainsKey(Dictionary dictionary, Object key){
        return dictionary.get(key) != null;
    }
}
