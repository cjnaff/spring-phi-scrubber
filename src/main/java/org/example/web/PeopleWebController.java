package org.example.web;

//import org.example.repository.PeopleRepository;
import org.example.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/peeps")
public class PeopleWebController {
    private final PeopleService peopleService;

    public PeopleWebController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPeeps(Model model) {
        model.addAttribute("peeps", this.peopleService.getAllPeople());
        return "people-list";
    }
}

