package com.graphqljava.examplemutations.GraphQL.Types;

import java.util.Objects;

public class CreateCustomerInput {

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name,email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCustomerInput that = (CreateCustomerInput) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(name, that.name);
    }
}
