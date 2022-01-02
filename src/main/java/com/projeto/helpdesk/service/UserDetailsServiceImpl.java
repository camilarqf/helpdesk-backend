package com.projeto.helpdesk.service;

import com.projeto.helpdesk.modelo.Pessoa;
import com.projeto.helpdesk.repository.PessoaRepository;
import com.projeto.helpdesk.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);
        if(pessoa.isPresent()){
            return new UserDetailsImpl(pessoa.get().getId(),pessoa.get().getEmail(),pessoa.get().getSenha(),pessoa.get().getPerfis());
        }
        throw new UsernameNotFoundException(email);
    }
}
