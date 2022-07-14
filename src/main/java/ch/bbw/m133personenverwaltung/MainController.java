package ch.bbw.m133personenverwaltung;

import ch.bbw.m133personenverwaltung.model.Person;
import ch.bbw.m133personenverwaltung.model.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;


@Controller
public class MainController {
    private final PersonRepository personRepository;

    @Autowired
    public MainController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping("/")
    public String index(Model model) {
        System.out.println(personRepository.findAll());
        model.addAttribute("persons", getPersons()); // getPersons()
        return "index";
    }

    @GetMapping("/seiteEditieren/{id}")
    public String editForm(Model model, @PathVariable long id) {
        model.addAttribute("person", personRepository.findById(id).get());
        return "personenBearbeitung";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("person", new Person());
        return "personenAdden";
    }

    @PostMapping("/personenAdden")
    public String addPerson(@ModelAttribute Person person, RedirectAttributes redirectAttributes, Model model) {
        try {
            person.setBirthdate(person.getBirthdate());
            personRepository.save(person);
            redirectAttributes.addFlashAttribute("success", "Person added");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("fail", "Person could not be added");
            e.printStackTrace();
        }
        model.addAttribute("person", person);
        return "redirect:/";
    }


    @PostMapping("/personenBearbeitung/{id}")
    public String editPerson(@PathVariable long id, @ModelAttribute Person person, RedirectAttributes attributes, Model model) {
        try {
            person.setBirthdate(person.getBirthdate());
            person.setId(id);
            personRepository.save(person);
            attributes.addFlashAttribute("success", "Person was successfully edited");
        } catch (Exception e) {
            attributes.addFlashAttribute("fail", "Person could not be edited");
            e.printStackTrace();
        }
        model.addAttribute("person", person);
        return "redirect:/";
    }

    @PostMapping("/personLoeschen/{id}")
    public String delUser(@PathVariable("id") long personId, RedirectAttributes redirectAttributes) {
        System.out.println(personId);
        try {
            personRepository.deleteById(personId);
            redirectAttributes.addFlashAttribute("success", "Person deleted");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("fail", "Person could not be deleted");
        }

        return "redirect:/";
    }


    private List<Person> getPersons() {
        List<Person> persons = personRepository.findAll();
        persons.stream().forEach(person -> {
            try {
                person.setToday(
                        DateConverter.convertDate(person.getBirthdate())
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return persons;
    }

}
