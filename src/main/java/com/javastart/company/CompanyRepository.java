package com.javastart.company;

import org.springframework.data.repository.CrudRepository;

interface CompanyRepository extends CrudRepository<Company, Long> {
}
