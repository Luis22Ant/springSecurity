package com.trabalhoFinal.TrabFinalJava.Controllers;

import com.trabalhoFinal.TrabFinalJava.Models.Item;
import com.trabalhoFinal.TrabFinalJava.Models.Reserva;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Repository.ItemRepository;
import com.trabalhoFinal.TrabFinalJava.Repository.ReservaRepository;
import com.trabalhoFinal.TrabFinalJava.Repository.UsuarioRepository;
import com.trabalhoFinal.TrabFinalJava.Services.ItemService;
import com.trabalhoFinal.TrabFinalJava.Services.ReservaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ItemService itemService;

    private final ReservaService reservaService;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ItemRepository itemRepository;

    public ReservaController(ItemService itemService, ReservaService reservaService) {
        this.itemService = itemService;
        this.reservaService = reservaService;
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        List<Item> itens = itemRepository.findAll();
        model.addAttribute("itens", itens);
        model.addAttribute("reserva", new Reserva());
        return "form-reserva";
    }

    @PostMapping("/novo")
    public String fazerReserva(@ModelAttribute("reserva") Reserva reserva) {
        Usuario membro = usuarioRepository.findById(1L).orElseThrow();
        reserva.setMembro(membro);

        reservaService.save(reserva);
        return "redirect:/reservas";
    }

    @PostMapping("/{id}/reserve")
    public String reserveItem(@PathVariable("id") Long itemId,
                              @RequestParam("diaReserva") String diaReserva,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Você precisa estar logado para reservar um item.");
            return "redirect:/login";
        }

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (reservaService.existsByItemAndData(item, diaReserva)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Este item já está reservado para a data selecionada.");
                return "redirect:/items";
            }

            Reserva reserva = new Reserva();
            reserva.setItem(item);
            reserva.setData(diaReserva);
            reserva.setMembro(usuario);

            reservaService.save(reserva);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva feita com sucesso!");
            return "redirect:/items";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Item não encontrado.");
            return "redirect:/items";
        }
    }


    @GetMapping("/editar-reserva/{id}")
    public String editarReserva(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Reserva> optionalReserva = reservaService.findById(id);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            model.addAttribute("reserva", reserva);
            return "formulario-editar-reserva";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Reserva não encontrada.");
            return "redirect:/items";
        }
    }

    @PostMapping("/editar-reserva/{id}")
    public String processarEdicaoReserva(@PathVariable("id") Long id, @ModelAttribute("reserva") Reserva reservaAtualizada, RedirectAttributes redirectAttributes) {
        Optional<Reserva> optionalReserva = reservaService.findById(id);
        if (optionalReserva.isPresent()) {
            Reserva reservaExistente = optionalReserva.get();
            reservaExistente.setData(reservaAtualizada.getData());
            reservaExistente.setItem(reservaAtualizada.getItem());

            reservaService.save(reservaExistente);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva atualizada com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Falha ao atualizar a reserva. Reserva não encontrada.");
        }
        return "redirect:/items";
    }

    @GetMapping("/remover-reserva/{id}")
    public String removerReserva(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Reserva> optionalReserva = reservaService.findById(id);
        if (optionalReserva.isPresent()) {
            reservaService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva removida com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Reserva não encontrada.");
        }
        return "redirect:/items";
    }
}
