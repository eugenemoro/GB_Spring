package ru.morozov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.morozov.editor.CategoryEditor;
import ru.morozov.editor.CompanyEditor;
import ru.morozov.entity.Ad;
import ru.morozov.entity.Category;
import ru.morozov.entity.Company;
import ru.morozov.repo.AdRepository;
import ru.morozov.repo.CategoryRepository;
import ru.morozov.repo.CompanyRepository;
import ru.morozov.soap.BillboardService;

import java.util.List;

@Controller
@SessionAttributes
@RequestMapping(value = {"/ads"}, method = RequestMethod.GET)
public class AdController {

	@Autowired
	private BillboardService billboardService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") String id, Model model) {
		Ad ad = billboardService.getAdByID(id);
		if (ad == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("ad", ad);
		return "ads/show";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddAdForm(Model model) {
		model.addAttribute("ad", new Ad());
		model.addAttribute("categories", billboardService.getListCategory());
		model.addAttribute("companies", billboardService.getListCompany());
		return "ads/create";
	}

	//show update form
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateAdForm(@PathVariable("id") String id, Model model) {
		Ad ad = billboardService.getAdByID(id);
		List<Category> categories = billboardService.getListCategory();
		categories.remove(ad.getCategory());
		model.addAttribute("categories", categories);
		model.addAttribute("ad", ad);
		return "ads/create";
	}

	//save ad to DB
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(@ModelAttribute("ad") Ad ad, BindingResult result, ModelMap model) {
		billboardService.addAd(ad);
		return "redirect:/";
	}

	//save ad to DB
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("ad") Ad ad, BindingResult result, ModelMap model) {
		billboardService.updateAd(ad);
		return "redirect:/";
	}

	//delete ad
	//TODO delete doesn't work
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String deleteAd(@PathVariable("id") String id, final RedirectAttributes redirectAttributes) {
		billboardService.deleteAdByID(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Ad is deleted!");
		return "redirect:/";
	}

	//initialize binder to implement company/category editors to get objects by ID
	@InitBinder
	protected void initBinder(WebDataBinder binder)     {
		binder.registerCustomEditor(Company.class, new CompanyEditor(billboardService.getCompanyRepository()));
		binder.registerCustomEditor(Category.class, new CategoryEditor(billboardService.getCategoryRepository()));
	}
}
