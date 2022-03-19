package com.example.RoutingApp.node;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class nodeService {
    public List<node> getNodes() {
		return Arrays.asList(
			new node(1L, "1", 1.0, 1.0)
		);
	}
    // I need to change this to an xml file.
}
