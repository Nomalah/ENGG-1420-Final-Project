package ca.uoguelph.storageelements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoteStorageElementTest {
    RemoteStorageElement folderElement;
    RemoteStorageElement fileElement;

    RemoteStorageElementTest() {
        if (!RemoteStorageElement.initialized)
            RemoteStorageElement.initLaserficheClient("bbCFNpkD2vPdVMvsT-JX", "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiYzdhMThlYWUtNGRhYi00OTk4LTllNzUtN2ZhOTdjNmZlMzdlIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogIlJ6Wkxrbzh2WWE0NEhPYzJ6T0djdld2ckNEYWRTRTJ1WkdTbDlDdmpRSmsiLAoJCSJ4IjogImJvRmJkeVBFbU96OGJ4cXdjaVltNDcwSjdDMXQzMWZhUmRwZ3hkdDNsMXMiLAoJCSJ5IjogIjZUT2M2QW82MFJjNVRyR2RfTXRiN3Q4UnJfM1ZPOWRtOEZyeUtWVllfemsiLAoJCSJkIjogIkVZaklmWUc5bjZyWUhpT1F3ejJHMWZxSG9fVURUc0p5eFFXSnRxNmxLRjQiLAoJCSJpYXQiOiAxNjc3Mjk3ODg4Cgl9Cn0");

        folderElement = new RemoteStorageElement("r-0001d410ba56", 23); // RandomText
        fileElement = new RemoteStorageElement("r-0001d410ba56", 25); // RandomText/LoremIpsum3
    }


    @Test
    void isDirectory() {
        assertEquals(folderElement.isDirectory(), true);
        assertEquals(fileElement.isDirectory(), false);
    }

    @Test
    void length() {
        assertEquals(folderElement.length(), 0);
        assertEquals(fileElement.length(), 3062); // ~2.99kB, Laserfiche rounds to 3kB
    }

    @Test
    void name() {
        assertEquals(folderElement.name(), "RandomText");
        assertEquals(fileElement.name(), "LoremIpsum3");
    }

    @Test
    void read() {
        assertEquals(folderElement.read(), folderElement.name());
        assertEquals(fileElement.read(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ligula mi, posuere at tincidunt in, sodales facilisis odio. Donec consequat tincidunt orci, id pretium nunc malesuada non. Donec ultricies mauris id lobortis sagittis. Donec tincidunt massa non ipsum vestibulum, a auctor est molestie. Nullam rutrum lectus vel fermentum tincidunt. Vivamus id pulvinar lacus. In porta fringilla metus, quis ultrices erat tempor id. Fusce tellus mauris, dapibus et molestie sed, ornare ut velit. Vestibulum nunc nisi, tincidunt tristique mollis in, dignissim vel purus. Sed pellentesque porttitor mauris in rutrum. Ut vitae tristique tellus, id gravida sem. Aliquam eu lorem at est pretium eleifend. Nulla a interdum lacus, ut eleifend diam. Suspendisse tincidunt, nulla sed condimentum tincidunt, nisl ipsum dignissim lorem, a pellentesque dolor lacus pharetra lectus.\r\n" +
            "\r\n" +
            "Suspendisse tempor feugiat diam, vel fermentum est sodales id. Integer elementum velit leo, eget vulputate ex tincidunt a. Aenean lacinia porttitor neque, a dictum enim aliquet in. Nullam nec iaculis risus. Suspendisse potenti. Sed et lectus dui. Aliquam porta luctus est non ultrices. Maecenas finibus erat et lacus imperdiet pulvinar nec ac leo. Donec vestibulum, lacus ac consequat aliquet, est magna tincidunt tortor, eu tincidunt lacus enim et sem. Donec accumsan, tortor vitae venenatis sodales, ex sem ultrices diam, sit amet finibus neque orci non velit.\r\n" +
            "\r\n" +
            "Ut lobortis nibh fermentum porttitor feugiat. Aenean faucibus dapibus eros non facilisis. Proin malesuada accumsan posuere. Mauris eu sapien lectus. In ultricies dolor vitae turpis suscipit, quis cursus sapien sodales. Aliquam volutpat nisi sed nulla ornare mollis. Proin rutrum, nisl sed auctor blandit, neque mauris imperdiet mauris, vel consectetur lorem ante eget lectus. Aliquam et placerat lorem. Aliquam hendrerit, leo sed finibus pulvinar, tortor tortor lacinia leo, in semper orci ligula eget dui. Suspendisse mollis viverra commodo. Sed auctor lectus ex, ac imperdiet libero pellentesque eget. In vitae efficitur metus. Nulla rhoncus lobortis pulvinar. Sed convallis feugiat elementum. Proin lacinia ligula eros, sit amet luctus felis imperdiet id.\r\n" +
            "\r\n" +
            "Ut fringilla nulla lorem, quis maximus eros mollis vitae. Donec eros nunc, bibendum at libero sed, gravida accumsan sapien. Phasellus accumsan diam in ultrices egestas. Quisque nunc justo, molestie sit amet nisi a, fringilla sollicitudin turpis. In mattis elit fringilla est efficitur, et commodo turpis finibus. Quisque quis feugiat est, tristique malesuada nibh. Aenean sit amet quam ac odio rhoncus vestibulum at a mi. Curabitur accumsan aliquet enim. Sed id vestibulum dolor. In non dapibus dui.\r\n" +
            "\r\n" +
            "Aliquam a nibh non ligula bibendum faucibus eget sit amet est. Praesent sit amet viverra arcu. Nam nec efficitur metus. Vestibulum tempor ac nulla at varius. Integer neque urna, elementum ut fermentum non, finibus eu diam. Mauris maximus vel nisl vitae interdum. Aliquam eget fringilla nisi, sit amet viverra eros. Donec vel enim est. Phasellus ut posuere ipsum."
        );
    }

    @Test
    void getChildStorageElements() {
        assertEquals(folderElement.getChildStorageElements().size(), 7);
        assertEquals(fileElement.getChildStorageElements().size(), 0);
    }
}
