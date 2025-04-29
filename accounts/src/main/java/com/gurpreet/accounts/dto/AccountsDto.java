package com.gurpreet.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number of the customer"
    )
    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of the Bank account", example = "Savings"
    )
    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Bank Branch Address"
    )
    @NotEmpty(message = "BranchAddress can not be null or empty")
    private String branchAddress;
}