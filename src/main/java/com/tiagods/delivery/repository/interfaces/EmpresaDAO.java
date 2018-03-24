package com.tiagods.delivery.repository.interfaces;

import com.tiagods.delivery.model.Empresa;

public interface EmpresaDAO {
    Empresa save(Empresa empresa);
    Empresa findById(Long id);
}
