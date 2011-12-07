package com.jbacon.test.tools;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;

public class RemoveTestFiles {

	public static void remove(final String name) throws IOException {
		File file = new File(name);
		assertThat(file.exists(), is(true));
		assertThat(file.canWrite(), is(true));

		if (file.isFile()) {
			removeFile(file);
		} else {
			removeDirectory(file);
		}

		assertThat(file.exists(), is(false));
	}

	private static void removeDirectory(final File directory) throws IOException {
		assertThat(directory.isDirectory(), is(true));

		for (File file : directory.listFiles()) {
			remove(file.getAbsolutePath());
		}

		directory.delete();
	}

	private static void removeFile(final File file) {
		assertThat(file.isFile(), is(true));

		file.delete();
	}
}
