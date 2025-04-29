package com.gurpreet.accounts.mapper;

import com.gurpreet.accounts.dto.AccountsDto;
import com.gurpreet.accounts.entity.Accounts;

public class AccountsMapper {

    /**
     * Maps an {@link Accounts} object to an {@link AccountsDto} object.
     *
     * @param accounts the object to be mapped
     * @param accountsDto the object to map to
     * @return the mapped object
     */
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    /**
     * Maps an {@link AccountsDto} object to an {@link Accounts} object.
     *
     * @param accountsDto the object to be mapped
     * @param accounts the object to map to
     * @return the mapped object
     */
    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

}
