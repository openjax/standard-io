/* Copyright (c) 2008 FastJAX
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package org.fastjax.io;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileUtilsTest {
  @Test
  public void testCommonality() {
    try {
      FileUtils.commonality();
      fail("Expected IllegalArgumentException");
    }
    catch (final IllegalArgumentException e) {
    }

    assertEquals(new File("foo"), FileUtils.commonality(new File("foo")));
    assertEquals(new File("/"), FileUtils.commonality(new File("/foo"), new File("/bar")));
    assertEquals(new File(""), FileUtils.commonality(new File("foo"), new File("bar")));

    final File[] files = {
      new File("/var/lib/foo/bar/hi.txt"),
      new File("/var/lib/foo/bar/a/b/c.txt"),
      new File("/var/lib/foo/welcome.txt"),
      new File("/var/lib/foo/some/dir.txt"),
      new File("/var/lib/foo/bar/a/b/hello.txt")
    };

    assertEquals(new File("/var/lib/foo"), FileUtils.commonality(files));
  }

  @Test
  public void testGetShortName() {
    assertEquals("", FileUtils.getShortName(new File("")));
    assertEquals("share", FileUtils.getShortName(new File("file:///usr/share/../share.txt")));
    assertEquals("lib", FileUtils.getShortName(new File("file:///usr/share/../share/../lib")));
    assertEquals("var", FileUtils.getShortName(new File("/usr/share/../share/../lib/../../var.old")));
    assertEquals("var", FileUtils.getShortName(new File("/usr/share/../share/../lib/../../var/")));
    assertEquals("resolv", FileUtils.getShortName(new File("/etc/resolv.conf")));
    assertEquals("name", FileUtils.getShortName(new File("name")));
  }
}