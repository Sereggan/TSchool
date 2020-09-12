package org.tsystems.tschool.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class TestRep {
    @Autowired
    private Environment environment;
}
