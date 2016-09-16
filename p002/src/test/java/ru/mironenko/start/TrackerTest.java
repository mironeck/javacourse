package ru.mironenko.start;

import org.hamcrest.Matcher;
import org.junit.Test;
import ru.mironenko.models.Comment;
import ru.mironenko.models.Filter;
import ru.mironenko.models.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Nikita on 14.09.2016.
 */
public class TrackerTest {
    @Test
    public void whenAddItems() throws Exception {
        Tracker tracker = new Tracker();

        Item itemOne = new Item("1","desc");

        assertThat(tracker.add(itemOne), is(itemOne));
    }

    @Test
    public void whenEditItem() throws Exception {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("1","desc");
        tracker.add(itemOne);

        Item checked = new Item("1a","test desc");
        checked.setId(itemOne.getId());

        tracker.edit(checked);

        assertThat(tracker.getItems()[0], is(checked));
    }

    @Test
    public void whenGetAllItems() throws Exception {
        Tracker tracker = new Tracker();

        Item itemOne = new Item("1","desc1");
        Item itemTwo = new Item("2","desc2");
        Item itemThree = new Item("3","desc3");

        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);

        Item[] checked = new Item[]{
            itemOne, itemTwo, itemThree
        };

        assertThat(tracker.getAll(), is(checked));
    }

    @Test
    public void whenDeleteItem() throws Exception {

        Tracker tracker = new Tracker();

        Item itemOne = new Item("1","desc1");
        Item itemTwo = new Item("2","desc2");
        Item itemThree = new Item("3","desc3");

        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);
        tracker.deleteItem(itemTwo);

        Item[] checked = new Item[]{itemOne, itemThree};

        assertThat(tracker.getAll(), is(checked));
    }

    @Test
    public void whenAddComment() throws Exception {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("1","desc1");
        tracker.add(itemOne);

        Comment comment = new Comment("Comment");
        tracker.addComment(itemOne.getId(), comment);

        assertThat(tracker.findById(itemOne.getId()).getComment()[0], is(comment));
    }

    @Test
    public void whenGetFilterItems() throws Exception {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("1","desc1");
        Item itemTwo = new Item("2","desc2");
        Item itemThree = new Item("3","test");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);

        Item [] checked = new Item[]{itemOne, itemTwo};

        assertThat(tracker.filterItems(new Filter("desc")), is(checked));
    }

}