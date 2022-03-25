package com.javastart.company;

import com.javastart.job_offer.JobOffer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyDtoMapper companyDtoMapper;
    private final CompanyJobOfferDtoMapper companyJobOfferDtoMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyDtoMapper companyDtoMapper, CompanyJobOfferDtoMapper companyJobOfferDtoMapper) {
        this.companyRepository = companyRepository;
        this.companyDtoMapper = companyDtoMapper;
        this.companyJobOfferDtoMapper = companyJobOfferDtoMapper;
    }

    Optional<CompanyDto> getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyDtoMapper::map);
    }

    List<CompanyJobOfferDto> getJobOffersByCompanyId(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            List<JobOffer> jobOffers = company.get().getJobOffers();
            if (jobOffers != null) {
                List<CompanyJobOfferDto> companyJobOfferDtoList = new ArrayList<>();
                for (JobOffer jobOffer : jobOffers) {
                    CompanyJobOfferDto companyJobOfferDto = companyJobOfferDtoMapper.map(jobOffer);
                    companyJobOfferDtoList.add(companyJobOfferDto);
                }
                return companyJobOfferDtoList;
            }
        }
        return Collections.emptyList();

/*        return companyRepository.findById(companyId)
                .map(Company::getJobOffers)
                .orElse(Collections.emptyList())
                .stream()
                .map(companyJobOfferDtoMapper::map)
                .toList();*/
    }
}
