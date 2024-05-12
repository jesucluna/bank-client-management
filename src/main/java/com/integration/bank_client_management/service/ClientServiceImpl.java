package com.integration.bank_client_management.service;

import com.integration.bank_client_management.common.GuardClauses;
import com.integration.bank_client_management.dto.ClientAddRequestDTO;
import com.integration.bank_client_management.dto.ClientDTO;
import com.integration.bank_client_management.dto.ClientDeleteResponseDTO;
import com.integration.bank_client_management.dto.ClientEditRequestDTO;
import com.integration.bank_client_management.model.Client;
import com.integration.bank_client_management.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ClientDTO> getAllClients(int page, int size) {
        List<ClientDTO> responseDTO = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clients = clientRepository.findAll(pageable);
        clients.forEach(client -> responseDTO.add(modelMapper.map(client, ClientDTO.class)));
        return responseDTO;
    }

    @Override
    public ClientDTO getClientById(int id) throws Exception {
        ClientDTO responseDTO;
        Optional<Client> client = clientRepository.findById(id);
        GuardClauses.guardAgainstClientNotFound(client);
        responseDTO = modelMapper.map(client, ClientDTO.class);
        return responseDTO;

    }

    @Override
    public ClientDTO addClient(ClientAddRequestDTO requestDTO) throws Exception {
        GuardClauses.guardAgainstClientAlreadyExists(clientRepository.findByEmail(requestDTO.getEmail()));
        Client newClient = clientRepository.save(new Client(requestDTO.getName(), requestDTO.getLastName(), requestDTO.getAddress(), requestDTO.getPhone(), requestDTO.getEmail(), requestDTO.getBirthDate()));
        return modelMapper.map(newClient, ClientDTO.class);
    }

    @Override
    public ClientDTO editClient(ClientEditRequestDTO requestDTO) throws Exception {
        Optional<Client> existingClient = clientRepository.findById(requestDTO.getId());
        GuardClauses.guardAgainstClientNotFound(existingClient);
        if (existingClient.isPresent() && !existingClient.get().getEmail().equals(requestDTO.getEmail())) {
            GuardClauses.guardAgainstClientAlreadyExists(clientRepository.findByEmail(requestDTO.getEmail()));
        }
        Client clientToUpdate = existingClient.get();
        clientToUpdate.setName(requestDTO.getName());
        clientToUpdate.setLastName(requestDTO.getLastName());
        clientToUpdate.setAddress(requestDTO.getAddress());
        clientToUpdate.setPhone(requestDTO.getPhone());
        clientToUpdate.setEmail(requestDTO.getEmail());
        Client updatedClient = clientRepository.save(clientToUpdate);
        return modelMapper.map(updatedClient, ClientDTO.class);
    }

    @Override
    public ClientDeleteResponseDTO deleteClient(int id) throws Exception {
        Optional<Client> existingClient = clientRepository.findById(id);
        GuardClauses.guardAgainstClientNotFound(existingClient);
        clientRepository.delete(existingClient.get());
        return new ClientDeleteResponseDTO(true);
    }
}