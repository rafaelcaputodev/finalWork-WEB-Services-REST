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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        setEverything(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    public void setEverything(ClientDTO dto,Client client){
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setName(dto.getName());
    }
}
