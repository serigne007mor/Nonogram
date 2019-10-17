import os
import xml.etree.ElementTree as ET

from xml.etree import ElementTree
from xml.dom import minidom

def prettify(elem):
    """Return a pretty-printed XML string for the Element.
    """
    rough_string = ElementTree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")

n_rows, n_cols = map(int, input().split())

rows = []
cols = []

for _ in range(n_rows):
    rows += [list(map(int, input().split()))]

for _ in range(n_cols):
    cols += [list(map(int, input().split()))]

instance = ET.Element("instance", {
    "format": "XCSP3",
    "type": "CSP"
})

# variables
variables = ET.SubElement(instance, 'variables')
array = ET.SubElement(variables, 'array', {
    "id": "x",
    "size": "[{0}][{1}]".format(n_rows, n_cols)
})
array.text = " 0 1 "

# constraints
constraints = ET.SubElement(instance, "constraints")

def to_regular_constraint(req, id, is_row, parent):
    regular = ET.SubElement(parent, "regular")

    # list
    lst = ET.SubElement(regular, "list")
    if is_row:
        lst.text = " x[{0}][] ".format(id)
    else:
        lst.text = " x[][{0}] ".format(id)

    # transitions
    n = 0
    transitions = " "
    pre = False

    for r in req:
        if pre:
            transitions += "(q{0},0,q{1})".format(n, n + 1)
            n += 1
        pre = True
        transitions += "(q{0},0,q{0})".format(n)
        for _ in range(r):
            transitions += "(q{0},1,q{1})".format(n, n + 1)
            n += 1
    transitions += "(q{0},0,q{0}) ".format(n)

    transitions_node = ET.SubElement(regular, "transitions")
    transitions_node.text = transitions

    # start
    start = ET.SubElement(regular, "start")
    start.text = " q0 "

    # final
    final = ET.SubElement(regular, "final")
    final.text = " q{0} ".format(n)

## rows
r_block = ET.SubElement(constraints, "block", { "class": "rows" })
for i in range(len(rows)):
    to_regular_constraint(rows[i], i, True, r_block)

## cols
c_block = ET.SubElement(constraints, "block", { "class": "columns" })
for i in range(len(cols)):
    to_regular_constraint(cols[i], i, False, c_block)

print(prettify(instance))