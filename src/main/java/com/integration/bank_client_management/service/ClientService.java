package com.integration.bank_client_management.service;

import com.integration.bank_client_management.dto.ClientAddRequestDTO;
import com.integration.bank_client_management.dto.ClientDTO;
import com.integration.bank_client_management.dto.ClientDeleteResponseDTO;
import com.integration.bank_client_management.dto.ClientEditRequestDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients(int page, int size);
    ClientDTO getClientById(int id) throws Exception;
    ClientDTO addClient(ClientAddRequestDTO requestDTO) throws Exception;
    ClientDTO editClient(ClientEditRequestDTO requestDTO) throws Exception;
    ClientDeleteResponseDTO deleteClient(int id) throws Exception;
}
