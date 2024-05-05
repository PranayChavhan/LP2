from queue import PriorityQueue

# Define the maze as a 2D list
# Each tuple (1, 1, 0, 0) represents the possible directions to go (right, down, left, up):
# '1' means there's an open path, and '0' means the path is blocked
maze_matrix = [
    [(1, 1, 0, 0), (1, 1, 1, 0), (0, 1, 1, 0), (0, 0, 0, 0)],
    [(1, 1, 0, 1), (1, 0, 1, 0), (0, 0, 1, 1), (0, 0, 0, 1)],
    [(0, 0, 0, 1), (1, 1, 1, 0), (1, 1, 1, 1), (0, 1, 1, 1)],
    [(1, 0, 0, 1), (0, 0, 1, 1), (0, 0, 1, 1), (0, 0, 0, 0)]
]

# Define the heuristic function (Manhattan distance)
def h(cell1, cell2):
    x1, y1 = cell1
    x2, y2 = cell2
    return abs(x1 - x2) + abs(y1 - y2)

# Define the A* function
def aStar(maze_matrix, start, goal):
    # Initialize g_score and f_score dictionaries
    g_score = {start: float('inf')}
    g_score[start] = 0
    f_score = {start: float('inf')}
    f_score[start] = h(start, goal)

    # Initialize the priority queue
    open_set = PriorityQueue()
    open_set.put((f_score[start], start))

    # Initialize the path dictionary
    aPath = {}

    # Start A* algorithm
    while not open_set.empty():
        _, current = open_set.get()
        if current == goal:
            break
        
        x, y = current
        # Define the four possible directions (right, down, left, up)
        directions = [(x, y + 1), (x + 1, y), (x, y - 1), (x - 1, y)]

        # Iterate over the directions
        for i, direction in enumerate(directions):
            dx, dy = direction
            # Check if the cell is within the maze boundaries
            if 0 <= dx < len(maze_matrix) and 0 <= dy < len(maze_matrix[0]):
                # Check if the path in the current direction is open
                if maze_matrix[x][y][i] == 1:
                    neighbor = (dx, dy)
                    # Calculate tentative g_score and f_score
                    tentative_g_score = g_score[current] + 1
                    tentative_f_score = tentative_g_score + h(neighbor, goal)

                    # If the tentative score is better, update the scores and the path
                    if tentative_f_score < f_score.get(neighbor, float('inf')):
                        g_score[neighbor] = tentative_g_score
                        f_score[neighbor] = tentative_f_score
                        open_set.put((tentative_f_score, neighbor))
                        aPath[neighbor] = current

    # Reconstruct the path from the start to the goal
    path = {}
    current = goal
    while current != start:
        prev = aPath[current]
        path[prev] = current
        current = prev

    return path

# Define a function to create a matrix of the successful path
def create_path_matrix(path, start, goal, maze_shape):
    # Initialize a 2D matrix with zeros
    path_matrix = [[0 for _ in range(maze_shape[1])] for _ in range(maze_shape[0])]

    # Trace the path and mark the cells with 1
    current = start
    path_matrix[current[0]][current[1]] = 1

    while current != goal:
        next_cell = path[current]
        path_matrix[next_cell[0]][next_cell[1]] = 1
        current = next_cell

    return path_matrix

# Define the main function
if __name__ == '__main__':
    # Define the start and goal coordinates
    start = (3, 3)
    goal = (0, 0)

    # Run the A* algorithm
    path = aStar(maze_matrix, start, goal)

    # Create the path matrix
    path_matrix = create_path_matrix(path, goal, start, maze_shape=(4, 4))

    # Print the path matrix
    print("Matrix of the successful path through the maze:")
    for row in path_matrix:
        print(row)
