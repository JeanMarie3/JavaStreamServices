package com.devskiller.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.devskiller.model.Author;
import com.devskiller.model.Book;
import com.devskiller.model.Genre;
import com.devskiller.model.Reader;

class BookSuggestionService {

	private final Set<Book> books;
	private final Set<Reader> readers;

	public BookSuggestionService(Set<Book> books, Set<Reader> readers) {
		this.books = books;
		this.readers = readers;
	}

	Set<String> suggestBooks(Reader reader) {
		//throw new UnsupportedOperationException("Please, implement me");

		Set<Genre> favGenres = reader.getFavouriteGenres();

		return books.stream()
				.filter(b -> b.getRating() >= 4)
				.filter(b -> favGenres.contains(b.getGenre()))
				.filter(b -> favBooksSameAge(reader).contains(b))
				.map(b -> b.getTitle())
				.collect(Collectors.toSet());

	}

	Set<String> suggestBooks(Reader reader, int rating) {
		//throw new UnsupportedOperationException("Please, implement me");

		Set<Genre> favGenres = reader.getFavouriteGenres();

		return books.stream()
				.filter(b -> b.getRating() >= rating)
				.filter(b -> favGenres.contains(b.getGenre()))
				.filter(b -> favBooksSameAge(reader).contains(b))
				.map(b -> b.getTitle())
				.collect(Collectors.toSet());
	}

	Set<String> suggestBooks(Reader reader, Author author) {
		//throw new UnsupportedOperationException("Please, implement me");

		Set<Genre> favGenres = reader.getFavouriteGenres();

		return books.stream()
				.filter(b -> b.getRating() >= 4)
				.filter(b -> favGenres.contains(b.getGenre()))
				.filter(b -> favBooksSameAge(reader).contains(b))
				.filter(b -> b.getAuthor().equals(author))
				.map(b -> b.getTitle())
				.collect(Collectors.toSet());
	}

	private Set<Book> favBooksSameAge(Reader reader) {
		//return null;
		Set<Reader> setReaders = readers.stream()
				.filter(r -> reader.getAge() == r.getAge())
				.filter(r -> !reader.equals(r))
				.collect(Collectors.toSet());

		Set<Book> favBooks = new HashSet<>();
		setReaders.forEach(r -> favBooks.addAll(r.getFavouriteBooks()));
		return favBooks;
	}

}
