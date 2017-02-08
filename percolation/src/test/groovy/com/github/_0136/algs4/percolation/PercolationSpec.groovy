package com.github._0136.algs4.percolation

import spock.lang.Specification

class PercolationSpec extends Specification {
    def percolation = new Percolation(8)

    def "New created Percolation should have no opened sites"() {
        expect:
        percolation.numberOfOpenSites() == 0
        !percolation.percolates()
    }

    def "After opening some site, it should be opened"() {
        when:
        percolation.open(1, 1)
        percolation.open(1, 2)
        percolation.open(2, 2)

        then:
        percolation.isOpen(1, 1)
        percolation.isOpen(1, 2)
        percolation.isOpen(2, 2)
    }

    def "For open (1, 1), (1, 2) and (2, 2) sites, (2, 2) should be full site"() {
        when:
        percolation.open(1, 1)
        percolation.open(1, 2)
        percolation.open(2, 2)

        then:
        percolation.isFull(2, 2)
    }

    def "Should be 3 opened site"() {
        when:
        percolation.open(1, 1)
        percolation.open(2, 2)
        percolation.open(3, 3)

        then:
        percolation.numberOfOpenSites() == 3
    }

    def "System should be percolate"() {
        when:
        println "|##   ###|"
        println "| ##     |"
        println "|   ##  #|"
        println "|##  #   |"
        println "|#   #  #|"
        println "|# ####  |"
        println "| # #    |"
        println "|    # ##|"
        percolation.open(1, 3)
        percolation.open(1, 4)
        percolation.open(1, 5)
        percolation.open(2, 1)
        percolation.open(2, 4)
        percolation.open(2, 5)
        percolation.open(2, 6)
        percolation.open(2, 7)
        percolation.open(2, 8)
        percolation.open(3, 1)
        percolation.open(3, 2)
        percolation.open(3, 3)
        percolation.open(3, 6)
        percolation.open(3, 7)
        percolation.open(4, 3)
        percolation.open(4, 4)
        percolation.open(4, 6)
        percolation.open(4, 7)
        percolation.open(4, 8)
        percolation.open(5, 2)
        percolation.open(5, 3)
        percolation.open(5, 4)
        percolation.open(5, 6)
        percolation.open(5, 7)
        percolation.open(6, 2)
        percolation.open(6, 7)
        percolation.open(6, 8)
        percolation.open(7, 1)
        percolation.open(7, 3)
        percolation.open(7, 5)
        percolation.open(7, 6)
        percolation.open(7, 7)
        percolation.open(7, 8)
        percolation.open(8, 1)
        percolation.open(8, 2)
        percolation.open(8, 3)
        percolation.open(8, 4)
        percolation.open(8, 6)

        then:
        percolation.percolates()
    }
}
