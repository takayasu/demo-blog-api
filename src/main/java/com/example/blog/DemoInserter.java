package com.example.blog;

import am.ik.blog.entry.*;
import com.example.blog.entry.EntryRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class DemoInserter implements CommandLineRunner {
	private final EntryRepository entryRepository;

	public DemoInserter(EntryRepository entryRepository) {
		this.entryRepository = entryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		entryRepository.create(entry(new EntryId(100L)));
		entryRepository.create(entry(new EntryId(101L)));
	}

	public static Entry entry(EntryId entryId) {
		EventTime now = EventTime.now();
		Categories categories = new Categories(new Category("foo"), new Category("bar"),
				new Category("hoge"));
		Tags tags = new Tags(new Tag("a"), new Tag("b"), new Tag("c"));
		return Entry.builder() //
				.entryId(entryId) //
				.content(new Content("- Hello " + entryId)) //
				.frontMatter(new FrontMatter(new Title("Entry " + entryId), categories,
						tags, now, now)) //
				.created(new Author(new Name("demo"), now)) //
				.updated(new Author(new Name("demo"), now)) //
				.build();
	}
}
