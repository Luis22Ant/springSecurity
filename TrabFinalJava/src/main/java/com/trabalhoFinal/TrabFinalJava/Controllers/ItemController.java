package com.trabalhoFinal.TrabFinalJava.Controllers;

import com.trabalhoFinal.TrabFinalJava.Enums.ETipoUser;
import com.trabalhoFinal.TrabFinalJava.Models.Item;
import com.trabalhoFinal.TrabFinalJava.Models.Reserva;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Services.ItemService;
import com.trabalhoFinal.TrabFinalJava.Services.ReservaService;
import com.trabalhoFinal.TrabFinalJava.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final UsuarioService usuarioService;
    private final ReservaService reservaService;

    @Autowired
    public ItemController(ItemService itemService, UsuarioService usuarioService,ReservaService reservaService) {
        this.itemService = itemService;
        this.usuarioService = usuarioService;
        this.reservaService = reservaService;
    }

    @GetMapping("")
    public String getAllItems(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("isAdminOrEmpregado", isAdminOrEmpregado(usuario));

        List<Reserva> reservasDoUsuario = reservaService.findByUserId(usuario.getId());
        List<Item> items = itemService.findAll();
        model.addAttribute("reservasDoUsuario", reservasDoUsuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("items", items);

        return "items";
    }

    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "item-form";
    }

    @PostMapping("/new")
    public String addItem(Item item, RedirectAttributes redirectAttributes) {
        System.out.println(item);
        itemService.save(item);
        redirectAttributes.addFlashAttribute("successMessage", "Item adicionado com sucesso!");
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String editItemForm(@PathVariable("id") Long id, Model model) {
        Optional<Item> optionalItem = itemService.findById(id);
        if (optionalItem.isPresent()) {
            model.addAttribute("item", optionalItem.get());
            return "item-form";
        } else {
            return "redirect:/items";
        }
    }

    @PostMapping("/{id}/edit")
    public String updateItem(@PathVariable("id") Long id, Item item, RedirectAttributes redirectAttributes) {
        item.setId(id);
        itemService.save(item);
        redirectAttributes.addFlashAttribute("successMessage", "Item atualizado com sucesso!");
        return "redirect:/items";
    }

    @PostMapping("/{id}/delete")
    public String deleteItem(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        itemService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Item excluído com sucesso!");
        return "redirect:/items";
    }

    @PostMapping("/{id}/remove-reservation")
    public String removeReservation(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Item> optionalItem = itemService.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();

            itemService.save(item);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva removida com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Falha ao remover a reserva.");
        }
        return "redirect:/items";
    }

    private boolean isAdminOrEmpregado(Usuario usuario) {
        return usuario != null && (usuario.getRole() == ETipoUser.ADMIN || usuario.getRole() == ETipoUser.EMPREGADO);
    }
}
