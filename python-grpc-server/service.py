import numpy as np
import matplotlib.pyplot as plt
import datetime


def generate_student_analysis():
    title_text = 'Student Analysis'
    footer_text = datetime.datetime.now().strftime("%c")
    data = [
        ['Student ID', 'Name', 'Semester', 'Department', 'CGPA'],
        [1, '123', 'abid', 11, 'CSE', 2.5],
        [2, '456', 'Sakib', 4, 'MNS', 3.6],
        [3, '789', 'Jawad', 7, 'EEE', 2.9],
    ]
    # Pop the headers from the data array
    column_headers = data.pop(0)
    row_headers = [x.pop(0) for x in data]

    cell_text = []
    for row in data:
        cell_text.append([x for x in row])

    rcolors = plt.cm.BuPu(np.full(len(row_headers), 0.1))
    ccolors = plt.cm.BuPu(np.full(len(column_headers), 0.1))

    the_table = plt.table(cellText=cell_text,
                          rowLabels=row_headers,
                          rowColours=rcolors,
                          rowLoc='right',
                          colColours=ccolors,
                          colLabels=column_headers,
                          loc='center')

    the_table.scale(1, 1.5)
    # Hide axes
    ax = plt.gca()
    ax.get_xaxis().set_visible(False)
    ax.get_yaxis().set_visible(False)
    plt.box(on=None)

    # title & footer
    plt.suptitle(title_text)
    plt.figtext(0.95, 0.05, footer_text, horizontalalignment='right', size=6, weight='light')

    plt.draw()
    fig = plt.gcf()
    plt.savefig('analysis.png',
                edgecolor=fig.get_edgecolor(),
                facecolor=fig.get_facecolor(),
                dpi=150
                )


generate_student_analysis()
