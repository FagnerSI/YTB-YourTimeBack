package br.com.ytb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ytb.model.Card;
import br.com.ytb.model.Usuario;
import br.com.ytb.repository.jpa.CardRepositorio;

@Controller
public class CardController {

	private List<Card> cards = new ArrayList<>();
	
	@Autowired
	private CardRepositorio cardRep;

	
	@RequestMapping("/card/{id}")
	public String card(HttpSession session, @PathVariable ("id") Card card) {	
		session.setAttribute("card", card);
		return "redirect:/minhas_tarefas";
	}

	@RequestMapping("/criar_card")
	public String criarCard() {
		return "criar_card";
	}    

	@PostMapping("/salvar_card")
	public String create(@ModelAttribute("card") Card card, HttpSession session) {		
		
		Usuario user = (Usuario) session.getAttribute("logado");			
		card.setUser(user);
		cardRep.save(card);

		return "redirect:/";
	}	

	@RequestMapping("/remove_card/{id}")
	public String remove(@PathVariable("id") Card card, HttpSession session) {		
				
		cardRep.delete(card);
    	
		return "redirect:/";
	}

	@GetMapping("/update_card/{id}")
	public String update(Model model, @PathVariable("id") Long id) {
		
		Card card = cardRep.findOne(id);
		model.addAttribute("card", card);
		return "update_card";	
	}

	@PostMapping("/update_card/update_c")
	public String update(Card card, HttpSession session){
	   
		Usuario user = (Usuario) session.getAttribute("logado");
	    card.setUser(user);
	    cardRep.save(card);
	    
	   		
		return "redirect:/";
	}
	
	@RequestMapping("/meus_cards")
	public String listaCards(Model model, HttpSession session) {
		Usuario user = (Usuario) session.getAttribute("logado");
		
		this.setCards(cardRep.findByUser(user));
		model.addAttribute("cards", cards);	
		
		return "index";
	}

	public List<Card> getCards() {		
		
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}


}