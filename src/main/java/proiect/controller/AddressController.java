package proiect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import proiect.entity.Address;
import proiect.entity.User;
import proiect.service.AddressService;
import proiect.service.UserService;

import javax.validation.Valid;

@Controller
public class AddressController {
    @Autowired
    UserService usersService;

    @Autowired
    AddressService addressService;

    public AddressController(UserService usersService, AddressService addressService) {
        this.usersService = usersService;
        this.addressService = addressService;
    }

    @GetMapping("/account")
    public String account(@AuthenticationPrincipal UserDetails currentUser,  Model model) {
        User user = usersService.findUser(currentUser.getUsername());
        Address address = user.getAddress();
        if (address != null) {
            model.addAttribute("address", address);
        } else {
            address = new Address();
            address.setUser(user);
            model.addAttribute("address", address);
        }

        return "/home/account";
    }

    @PostMapping("/address")
    public String saveOrUpdateAddress(@Valid @ModelAttribute Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/home/account";
        }

        Address savedAddress = addressService.save(address);

        return "redirect:/account" ;
    }
}
