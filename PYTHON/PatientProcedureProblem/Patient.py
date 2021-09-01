class Patient:
    def __init__(self,f,m,l,address,city,state,ZIP,phone,emergency_name,emergency_contact):
        self.__f = f
        self.__m = m
        self.__l = l
        self.__address = address
        self.__city = city
        self.__state = state
        self.__ZIP = ZIP
        self.__phone = phone
        self.__emergency_name = emergency_name
        self.__emergency_contact = emergency_contact

    def set_f(self,f):
        self.__f = f
    def set_m(self,m):
        self.__m = m
    def set_l(self):
        self.__l = l
    def set_address(self,address):
        self.__address = address
    def set_city(self,city):
        self.__city = city
    def set_state(self,state):
        self.__state = state
    def set_ZIP(self,ZIP):
        self.__ZIP = ZIP
    def set_phone(self,phone):
        self.__phone = phone
    def set_emergency_name(self,emergency_name):
        self.__emergency_name = emergency_name
    def set_emergency_contact(self, emergency_contact):
        self.__emergency_contact = emergency_contact

    def get_f(self):
        return self.__f
    def get_m(self):
        return self.__m
    def get_l(self):
        return self.__l
    def get_address(self):
        return self.__address
    def get_city(self):
        return self.__city
    def get_state(self):
        return self.__state
    def get_ZIP(self):
        return self.__ZIP
    def get_phone(self):
        return self.__phone
    def get_emergency_name(self):
        return self.__emergency_name
    def get_emergency_contact(self):
        return self.__emergency_contact

    def __str__(self):
        return "First Name: " + self.__f + "\nMiddle Name: " + self.__m + "\nLast Name: " + self.__l + "\nAddress: " + self.__address + "\nCity: " + self.__city + "\nState: " + self.__state + "\nZIP: " + str(self.__ZIP) + "\nPhone: " + self.__phone + "\nEmergency Contact: " + self.__emergency_name + "\nEmergency Phone: " + self.__emergency_contact
