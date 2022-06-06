package com.example.professor_allocation;

public class DepartamentoResponse {

    private int id;

    private String name;

    public DepartamentoResponse() {
    }

    public DepartamentoResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
