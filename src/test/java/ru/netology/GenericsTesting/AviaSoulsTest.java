package ru.netology.GenericsTesting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AviaSoulsTest {

    //  -----       TicketTest      -----   //

    @Test // первый билет дешевле
    public void shouldReturnNegativeWhenFirstTicketIsCheaper() {
        Ticket ticket1 = new Ticket("LED", "KZN", 2500, 8, 10);
        Ticket ticket2 = new Ticket("LED", "KZN", 4500, 12, 15);

        assertTrue(ticket1.compareTo(ticket2) < 0);
    }

    @Test // цены одинаковы
    public void shouldReturnZeroWhenPricesAreEqual() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3500, 9, 11);
        Ticket ticket2 = new Ticket("LED", "KZN", 3500, 13, 16);

        assertEquals(0, ticket1.compareTo(ticket2));
    }

    @Test // первый билет дороже
    public void shouldReturnPositiveWhenFirstTicketIsMoreExpensive() {
        Ticket ticket1 = new Ticket("LED", "KZN", 7000, 7, 10);
        Ticket ticket2 = new Ticket("LED", "KZN", 4000, 15, 17);

        assertTrue(ticket1.compareTo(ticket2) > 0);
    }

    @Test // билет равен самому себе
    public void shouldReturnTrueWhenComparingTicketWithItself() {
        Ticket ticket = new Ticket("LED", "KZN", 3200, 9, 11);

        assertEquals(ticket, ticket);
    }

    @Test // билеты с одинаковыми данными равны
    public void shouldReturnTrueWhenTicketsHaveSameFields() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3200, 9, 11);
        Ticket ticket2 = new Ticket("LED", "KZN", 3200, 9, 11);

        assertEquals(ticket1, ticket2);
    }

    @Test // билеты с разной ценой не равны
    public void shouldReturnFalseWhenTicketPricesDiffer() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3200, 9, 11);
        Ticket ticket2 = new Ticket("LED", "KZN", 4200, 9, 11);

        assertNotEquals(ticket1, ticket2);
    }

    @Test // билеты с разным временем вылета не равны
    public void shouldReturnFalseWhenDepartureTimesDiffer() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3200, 8, 11);
        Ticket ticket2 = new Ticket("LED", "KZN", 3200, 9, 11);

        assertNotEquals(ticket1, ticket2);
    }

    @Test // билеты с разным временем прилёта не равны
    public void shouldReturnFalseWhenArrivalTimesDiffer() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3200, 8, 11);
        Ticket ticket2 = new Ticket("LED", "KZN", 3200, 8, 12);

        assertNotEquals(ticket1, ticket2);
    }

    @Test // билеты с разными аэропортами вылета не равны
    public void shouldReturnFalseWhenDepartureAirportsDiffer() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3200, 8, 11);
        Ticket ticket2 = new Ticket("MSK", "KZN", 3200, 8, 11);

        assertNotEquals(ticket1, ticket2);
    }

    @Test // билеты с разными аэропортами прилёта не равны
    public void shouldReturnFalseWhenArrivalAirportsDiffer() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3200, 8, 11);
        Ticket ticket2 = new Ticket("LED", "MSK", 3200, 8, 11);

        assertNotEquals(ticket1, ticket2);
    }

    @Test // билет не равен null
    public void shouldReturnFalseWhenComparingTicketWithNull() {
        Ticket ticket = new Ticket("LED", "KZN", 3200, 8, 11);

        assertNotEquals(ticket, null);
    }

    @Test // билет не равен объекту другого класса
    public void shouldReturnFalseWhenComparingTicketWithDifferentClass() {
        Ticket ticket = new Ticket("LED", "KZN", 3200, 8, 11);
        Object other = new Object();

        assertNotEquals(ticket, other);
    }

    @Test // одинаковые билеты имеют одинаковый hashCode
    public void shouldReturnSameHashCodeForEqualTickets() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3200, 8, 11);
        Ticket ticket2 = new Ticket("LED", "KZN", 3200, 8, 11);

        assertEquals(ticket1.hashCode(), ticket2.hashCode());
    }

    @Test // изменение данных может изменить hashCode
    public void shouldCalculateHashCodeForDifferentTickets() {
        Ticket ticket1 = new Ticket("LED", "KZN", 3200, 8, 11);
        Ticket ticket2 = new Ticket("LED", "KZN", 4200, 8, 11);

        assertNotEquals(ticket1.hashCode(), ticket2.hashCode());
    }


    //      -----   AviaSoulsTest   -----       //

    @Test // поиск билетов с сортировкой по цене
    public void shouldReturnTicketsSortedByPrice() {
        AviaSouls manager = new AviaSouls();

        Ticket expensive = new Ticket("LED", "KZN", 9000, 8, 11);
        Ticket cheap = new Ticket("LED", "KZN", 2500, 12, 14);
        Ticket medium = new Ticket("LED", "KZN", 5500, 16, 19);
        Ticket otherRoute = new Ticket("LED", "MSK", 1800, 7, 8);

        manager.add(expensive);
        manager.add(cheap);
        manager.add(medium);
        manager.add(otherRoute);

        Ticket[] actual = manager.search("LED", "KZN");

        Ticket[] expected = {cheap, medium, expensive};

        assertArrayEquals(expected, actual);
    }

    @Test // билеты по маршруту не найдены
    public void shouldReturnEmptyArrayWhenNoTicketsFound() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("LED", "KZN", 2500, 8, 10));

        Ticket[] actual = manager.search("LED", "MSK");

        assertEquals(0, actual.length);
    }

    @Test // найден только один билет
    public void shouldReturnSingleTicketWhenOnlyOneMatches() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket = new Ticket("LED", "KZN", 4200, 9, 12);
        manager.add(ticket);
        manager.add(new Ticket("LED", "MSK", 1800, 13, 14));

        Ticket[] actual = manager.search("LED", "KZN");

        assertArrayEquals(new Ticket[]{ticket}, actual);
    }

    @Test // поиск билетов с сортировкой по времени перелёта
    public void shouldSearchAndSortTicketsByFlightTime() {
        AviaSouls manager = new AviaSouls();

        Ticket fourHours = new Ticket("LED", "KZN", 4200, 8, 12);
        Ticket twoHours = new Ticket("LED", "KZN", 7500, 13, 15);
        Ticket threeHours = new Ticket("LED", "KZN", 5800, 17, 20);
        Ticket otherRoute = new Ticket("LED", "MSK", 1800, 7, 8);

        manager.add(fourHours);
        manager.add(twoHours);
        manager.add(threeHours);
        manager.add(otherRoute);

        Ticket[] actual = manager.searchAndSortBy(
                "LED",
                "KZN",
                new TicketTimeComparator()
        );

        Ticket[] expected = {twoHours, threeHours, fourHours};

        assertArrayEquals(expected, actual);
    }

    @Test // получение всех билетов
    public void shouldReturnAllAddedTickets() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("LED", "KZN", 4200, 9, 12);
        Ticket ticket2 = new Ticket("LED", "MSK", 2800, 14, 16);

        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] actual = manager.findAll();

        Ticket[] expected = {ticket1, ticket2};

        assertArrayEquals(expected, actual);
    }

    @Test // получение пустого массива билетов
    public void shouldReturnEmptyArrayWhenNoTicketsAdded() {
        AviaSouls manager = new AviaSouls();

        assertEquals(0, manager.findAll().length);
    }

    @Test // поиск билета при совпадении аэропорта вылета
    public void shouldFindTicketWhenDepartureAirportMatches() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket = new Ticket("LED", "KZN", 4200, 9, 12);
        manager.add(ticket);

        Ticket[] actual = manager.search("LED", "KZN");

        assertArrayEquals(new Ticket[]{ticket}, actual);
    }

    @Test // аэропорт вылета не совпадает
    public void shouldIgnoreTicketWhenDepartureAirportDoesNotMatch() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("LED", "KZN", 4200, 9, 12));

        Ticket[] actual = manager.search("MSK", "KZN");

        assertEquals(0, actual.length);
    }


    //      -----       TicketTimeComparatorTest        -----       //

    @Test // первый перелёт короче второго
    public void shouldReturnNegativeWhenFirstFlightIsShorter() {
        Ticket ticket1 = new Ticket("LED", "KZN", 4200, 8, 10);
        Ticket ticket2 = new Ticket("LED", "KZN", 7500, 12, 15);

        TicketTimeComparator comparator = new TicketTimeComparator();

        assertTrue(comparator.compare(ticket1, ticket2) < 0);
    }

    @Test // продолжительность перелётов одинаковая
    public void shouldReturnZeroWhenFlightTimesAreEqual() {
        Ticket ticket1 = new Ticket("LED", "KZN", 4200, 8, 10);
        Ticket ticket2 = new Ticket("LED", "KZN", 7500, 12, 14);

        TicketTimeComparator comparator = new TicketTimeComparator();

        assertEquals(0, comparator.compare(ticket1, ticket2));
    }

    @Test // первый перелёт длиннее второго
    public void shouldReturnPositiveWhenFirstFlightIsLonger() {
        Ticket ticket1 = new Ticket("LED", "KZN", 4200, 8, 12);
        Ticket ticket2 = new Ticket("LED", "KZN", 7500, 12, 14);

        TicketTimeComparator comparator = new TicketTimeComparator();

        assertTrue(comparator.compare(ticket1, ticket2) > 0);
    }

    @Test // проверка сортировки билетов по времени полёта
    public void shouldSortTicketsByFlightTime() {
        Ticket[] tickets = {
                new Ticket("LED", "KZN", 4200, 8, 12),  // 4 часа
                new Ticket("LED", "KZN", 7500, 13, 15), // 2 часа
                new Ticket("LED", "KZN", 5800, 17, 20)  // 3 часа
        };

        Arrays.sort(tickets, new TicketTimeComparator());

        assertEquals(7500, tickets[0].getPrice()); // 2 часа
        assertEquals(5800, tickets[1].getPrice()); // 3 часа
        assertEquals(4200, tickets[2].getPrice()); // 4 часа
    }
}

