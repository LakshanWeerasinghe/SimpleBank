package com.cse.database.models.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @Column(name = "branch_id", length = 50)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

}
