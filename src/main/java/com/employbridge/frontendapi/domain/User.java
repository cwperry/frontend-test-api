package com.employbridge.frontendapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(
        get = { "is*", "get*" },                      // Detect 'get' and 'is' prefixes in accessor methods
        init = "with*",                               // Builder initialization methods will have 'with' prefix
        typeImmutable = "Immutable*",                 // 'Immutable' prefix will be added to the generated class
        optionalAcceptNullable = true,                // For optional fields, withX(null) will be treated as empty
        jdkOnly = true,                               // Generated code will use only JDK 7+ standard library classes
        visibility = Value.Style.ImplementationVisibility.PUBLIC) // Generated class will be always public
@JsonSerialize(as = ImmutableUser.class)
@JsonDeserialize(as = ImmutableUser.class)
public interface User {
    int id();
    @JsonProperty("first_name")
    String firstName();
    @JsonProperty("last_name")
    String lastName();
    String email();
    String gender();
    @JsonProperty("company_name")
    String companyName();
}
