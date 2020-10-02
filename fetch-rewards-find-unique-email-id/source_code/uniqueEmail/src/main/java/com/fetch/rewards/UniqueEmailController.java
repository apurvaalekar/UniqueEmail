package com.fetch.rewards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniqueEmailController {

	@Value("${emailListPath}")
	private String emailListPath;

	@GetMapping("/index")
	public int findUniqueEmailId(@RequestParam(value = "emailIds", required = false) List<String> emailIds) {

		Set<String> unique = new HashSet<String>();

		if (emailIds == null || emailIds.size() == 0) {
			if (emailIds == null)
				emailIds = new ArrayList<String>();

			if (emailListPath != null || emailListPath.length() != 0) {
				try {
					BufferedReader bf = new BufferedReader(new FileReader(emailListPath));
					String line;
					while ((line = bf.readLine()) != null && line.trim().length() > 0) {
						String[] parts = line.split(",");
						for (int i = 0; i < parts.length; i++) {
							emailIds.add(parts[i]);
						}
					}
					bf.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		for (String email : emailIds) {
			String[] parts = email.split("@");
			if (parts.length == 2) {
				String correct = parts[0].replaceAll("\\.", "");
				if (parts[0].indexOf("+") > -1) {
					correct = correct.split("\\+")[0];
				}
				String toAdd = correct + "@" + parts[1];
				if (!unique.contains(toAdd) && toAdd.trim().length() != 0) {
					unique.add(toAdd);
				}
			}
		}
		return unique.size();

	}

}
