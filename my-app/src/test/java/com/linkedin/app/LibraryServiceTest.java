package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {
    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private LibraryRepository libraryRepository;

    @Test
    public void addBookTest(){
        libraryService.addBook("Book 1", 1);
        verify(libraryRepository).addBook(eq("Book 1"), eq(1));
    }

    @Test
    public void lendBookTestTrue() {
        libraryService.registerMember("001", "First");
        when(libraryRepository.lendBook(eq("First"))).thenReturn(true);
        boolean result = libraryService.lendBook("First", "001");
        assertTrue(result);
        verify(libraryRepository).lendBook(eq("First"));
    }

    @Test
    public void lendBookTestFalse() {
        assertFalse(libraryService.lendBook("First", "001"));
    }

    @Test
    public void registerMemberTest(){
        libraryService.registerMember("001", "First");
       assertTrue(libraryService.getMembers().containsKey("001"));
       assertTrue(libraryService.getMembers().get("001").getName().equals("First"));
    }

}
