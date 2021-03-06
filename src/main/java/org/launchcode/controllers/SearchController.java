package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.launchcode.models.JobData.findAll;
import static org.launchcode.models.JobData.findByColumnAndValue;
import static org.launchcode.models.JobData.findByValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        if (searchType.equals("all") && searchTerm.equals("")){
            model.addAttribute("jobs",findAll());
            model.addAttribute("columns", ListController.columnChoices);
        return "search";
        }
        else if(searchType.equals("all") && !searchTerm.equals("")){
            model.addAttribute("jobs",findByValue(searchTerm));
            model.addAttribute("columns", ListController.columnChoices);
            return "search";
        }
        model.addAttribute("jobs",findByColumnAndValue(searchType,searchTerm));
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
        }

    // TODO #1 - Create handler to process search request and display results

}