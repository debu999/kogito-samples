package org.doogle.kogito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Sample implements Serializable {
    
    public String test;
    public Integer numberTest;
}
