package ru.mironenko.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import ru.mironenko.control.UserStore;

import org.powermock.api.mockito.PowerMockito;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserStore.class)

public class RoleServletsTests {

    @Mock RequestDispatcher dispatcher;
    @Mock UserStore userStore;

    @Before
    public void init(){
        PowerMockito.mockStatic(UserStore.class);
        PowerMockito.when(UserStore.getInstance()).thenReturn(userStore);
    }

    @Test
    public void whenCreateRoleThenShouldReturnRoleName() throws ServletException, IOException {

        UserStore userStore = UserStore.getInstance();

        CreateRole createRole = new CreateRole();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("role_id")).thenReturn("5");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getRequestDispatcher("/WEB-INF/views/CreateRole.jsp")).thenReturn(dispatcher);

        createRole.doPost(request, response);

        String name = userStore.getRole("test").getName();

        assertThat(name, is("test"));
    }


    @Test
    public void whenEditRoleThenShouldReturnNewRoleName() throws ServletException, IOException {

        UserStore userStore = UserStore.getInstance();

        EditRole editRole = new EditRole();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("role_id")).thenReturn("5");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("roleNewId")).thenReturn("6");
        when(request.getParameter("newName")).thenReturn("testTwo");
        when(request.getRequestDispatcher("/WEB-INF/views/CreateRole.jsp")).thenReturn(dispatcher);

        editRole.doPost(request, response);

        String name = userStore.getRole("testTwo").getName();

        assertThat(name, is("testTwo"));
    }


    @Test
    public void whenDeleteRoleThenShouldReturnNull() throws ServletException, IOException {

        UserStore userStore = UserStore.getInstance();

        DeleteRole deleteRole = new DeleteRole();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        userStore.createNewRole(0, "delete");

        when(request.getParameter("role_id")).thenReturn("0");
        when(request.getParameter("name")).thenReturn("delete");
        when(request.getRequestDispatcher("/WEB-INF/views/CreateRole.jsp")).thenReturn(dispatcher);

        deleteRole.doPost(request, response);

        assertNull(userStore.getRole("delete"));
    }

}
