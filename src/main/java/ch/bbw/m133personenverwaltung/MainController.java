package ch.bbw.m133personenverwaltung;

import ch.bbw.m133personenverwaltung.model.Person;
import ch.bbw.m133personenverwaltung.model.PersonRepository;
import ch.qos.logback.classic.pattern.DateConverter;
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
    private PersonRepository personRepository;

    private List<Person> getPersons() {
        List<Person> persons = personRepository.findAll();
        persons.forEach(person -> {
            try {
                person.setToday(person.getBirthdate())(
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return persons;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("person", new Person());
        return "add_person";
    }

    @GetMapping("/")    public String index(Model model) {
        model.addAttribute("persons", getPersons());
        return "index";
    }

    @GetMapping("/personenBearbeitung")
    public String personenBearbeitung() {
        return "personenBearbeitung";
    }

    @PostMapping("/personenAdden")
    public String addPerson(@ModelAttribute Person person, RedirectAttributes redirectAttributes) {
        try {
            person.setBirthdate(DateConverter.dbDate(person.getBirthdate()));
            personRepository.save(person);
            redirectAttributes.addFlashAttribute("success", "Person added");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("fail", "Person could not be added");
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/personBearbeitung/{id}")
    public String editPerson(@PathVariable long id, @ModelAttribute Person person, RedirectAttributes attributes) {
        try {
            person.setBirthdate(DateConverter.dbDate(person.getBirthdate()));
            person.setId(id);
            personRepository.save(person);
            attributes.addFlashAttribute("success", "Person was successfully edited");
        } catch (Exception e) {
            attributes.addFlashAttribute("fail", "Person could not be edited");
            e.printStackTrace();
        }
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
    @GetMapping("/seiteEditieren/{id}")
    public String editForm(Model model, @PathVariable long id) {
        model.addAttribute("person", personRepository.findById(id).get());
        return "seiteEditieren";
    }

}
