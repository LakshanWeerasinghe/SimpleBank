package com.cse.database.controllers.api;

import com.cse.database.controllers.request.savingsaccount.AddNewSavingsAccountRequest;
import com.cse.database.controllers.request.savingsaccount.DepositRequest;
import com.cse.database.dto.dto.DepositDto;
import com.cse.database.exception.BRSException;
import com.cse.database.models.savings.account.SavingsAccount;
import com.cse.database.models.savings.account.SavingsOpen;
import com.cse.database.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/savingsAccount")
public class SavingsController {

    @Autowired
    private SavingsAccountService accountService;


    @PostMapping
    public ResponseEntity openNewSavingsAccount(@RequestBody @Valid AddNewSavingsAccountRequest addNewSavingsAccountRequest){

        Map<String, Object> body = new HashMap<>();

        List<String> errors = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        SavingsAccount savingsAccount = new SavingsAccount().setAccountNumber(addNewSavingsAccountRequest.getAccountNumber())
                                                .setBalance(addNewSavingsAccountRequest.getAmount())
                                                .setStatus(SavingsAccount.Status.OPEN);

        SavingsOpen savingsOpen = new SavingsOpen().setAccount(savingsAccount)
                                            .setAccountNumber(savingsAccount.getAccountNumber())
                                            .setOpenedDate(new Date());

        try {

            accountService.openSavingsAccount(savingsAccount, savingsOpen, addNewSavingsAccountRequest.getAccountType(),
                                            auth.getName(), addNewSavingsAccountRequest.getNic());

            body.put("Success" , "Account creation Success");

            return ResponseEntity.ok().body(body);

        }
        catch (BRSException.DuplicateEntityException ex){
            errors.add(ex.getMessage());
        }
        catch (BRSException.EntityNotFoundException ex){
            errors.add(ex.getMessage());
        }

        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestBody @Valid DepositRequest depositRequest){

        Map<String, Object> body = new HashMap<>();

        List<String> errors = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        DepositDto depositDto = new DepositDto().setAccountNumber(depositRequest.getAccountNumber())
                                                .setAmount(depositRequest.getAmount())
                                                .setDate(new Date())
                                                .setEmpUsername(auth.getName());

        try {
            accountService.deposit(depositDto);

            body.put("Success", "Money Deposit Successful");

            return ResponseEntity.ok().body(body);

        }
        catch (BRSException.EntityNotFoundException ex){
            errors.add(ex.getMessage());
        }
        catch (BRSException.AccountExpiredException ex){
            errors.add(ex.getMessage());
        }

        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);

    }
}
