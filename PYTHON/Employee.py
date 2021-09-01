class Employee:
    
    # Initial values for the employee
    def __init__(self, name, ID_number, department, job_title):
        self.__name = name
        self.__ID_number = ID_number
        self.__department = department
        self.__job_title = job_title
        
    # Changes the name of the employee
    def set_name(self, name):
        self.__name = name
        
    # Changes the ID of the employee
    def set_ID_number(self, ID_number):
        self.__ID_number = ID_number
        
    # Changes the department of the employee
    def set_department(self, department):
        self.__department = department
        
    # Changes the job title of the employee
    def set_job_title(self, job_title):
        self.__job_title = job_title
        
    # Gets the employee's name
    def get_name(self):
        return self.__name

    # Gets tbe employee's ID
    def get_ID_number(self):
        return self.__ID_number

    # Gets the employee's department
    def get_department(self):
        return self.__department

    # Gets the employee's job title
    def get_job_title(self):
        return self.__job_title

    # Compiles the data into one employee sheet
    def __str__(self):
        return "Name: " + self.__name + "\nID number: " + str(self.__ID_number) + "\nDepartemnt: " + self.__department + "\nTitle: " + self.__job_title + "\n\n"
    
    
