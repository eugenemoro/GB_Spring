package ru.morozov.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.morozov.entity.Ad;
import ru.morozov.entity.Category;
import ru.morozov.entity.Company;
import ru.morozov.repo.AdRepository;
import ru.morozov.repo.CategoryRepository;
import ru.morozov.repo.CompanyRepository;

import javax.jws.WebService;
import java.util.List;

@Component
@WebService
public class BillboardService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void addAd(Ad ad) {
        adRepository.saveAndFlush(ad);
    }

    public void deleteAdByID(String id) {adRepository.delete(id);}

    public Ad getAdByID(String id) {
        return adRepository.findOne(id);
    }

    public void updateAd(Ad ad) {
        adRepository.updateAd(ad.getId(), ad.getName(), ad.getCategory(), ad.getContent(), ad.getPhoneNumber());
        adRepository.flush();
    }

    public List<Ad> getListAd() {
        return adRepository.findAll();
    }

    public List<Ad> getListAdByCategory(Category category) {
        return adRepository.findByCategory(category);
    }

    public Company getCompanyByAd(Ad ad){
        return companyRepository.findOne(ad.getId());
    }

    public List<Category> getListCategory() {
        return categoryRepository.findAll();
    }

    public List<Company> getListCompany() {
        return companyRepository.findAll();
    }

    //TODO create getRepository(someParam) to return repos of different types
    public CompanyRepository getCompanyRepository() {
        return companyRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }
}
