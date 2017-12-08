package br.com.ytb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ytb.model.Card;
import br.com.ytb.model.Tarefa;
import br.com.ytb.repository.jpa.TarefaRepositorio;

@Controller
public class TarefaController {	
	
	
	@Autowired
    private TarefaRepositorio tarefaRep;
		
	
	@RequestMapping("/criar_tarefa")
	public String criarCard() {
		return "criar_tarefa";
	}

	@PostMapping("/salvar_tarefa")
	public String create(Tarefa tarefa, HttpSession session) {
		
		Card card = (Card) session.getAttribute("card");		
		tarefa.setCard(card);		
		tarefaRep.save(tarefa);	
		
		return "forward:/minhas_tarefas";
	}	
	
	@RequestMapping("/remove_tarefa/{id}")
	public String remove(@PathVariable("id") Tarefa tarefa, HttpSession session) {				
				
		tarefaRep.delete(tarefa);
			
		return "redirect:/minhas_tarefas";
	}
	
	@RequestMapping("/update_tarefa/{id}")
	public String update(Model model, @PathVariable("id") Long id) {
		
		Tarefa tarefa = tarefaRep.findOne(id);
		model.addAttribute("tarefa", tarefa);
		
		return "update_tarefa";	
	}		
	
	@PostMapping("/update_tarefa/update_t")
	public String update(HttpSession session, Tarefa tarefa){
		
		Card card = (Card) session.getAttribute("card");		
		tarefa.setCard(card);
		tarefaRep.save(tarefa);
		
		return "redirect:/minhas_tarefas";
	}
	
	@RequestMapping("/minhas_tarefas")
	public String listaTarefas(Model model, HttpSession session) {
		Card card = (Card) session.getAttribute("card");		
		model.addAttribute("tarefas", tarefaRep.findByCard(card));
		return "card";
	}
	
	

}
