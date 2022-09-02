package net.edu.module.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface ProblemService {

    void savaProblem(Object object);

    void updateProblem(Object object);

    List<Object> getProblemList(Object object);


}
