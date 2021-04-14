package com.caputo.finalWork.Services;

import com.caputo.finalWork.Services.Exceptions.NotFoundException;
import com.caputo.finalWork.dto.ClientDTO;
import com.caputo.finalWork.entities.Client;
import com.caputo.finalWork.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPage(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new NotFoundException("ERROR::"));
        return new ClientDTO(client);
    }
}
