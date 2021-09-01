import Patient
import Procedure

patient = Patient.Patient("Mary","Jones","Smith","1738 Blue Ridge Drive","Harrisonburg","Virgina",22801,"703-803-0219","John Smith","703-291-1199")
procedure_1 = Procedure.Procedure("Physical Exam","8-24-2019","Dr. Irvine",250.00)
procedure_2 = Procedure.Procedure("X-ray","8-24-2019","Dr. Jamison",500.00)
procedure_3 = Procedure.Procedure("Blood Test","8-24-2019","Dr. Smith",200.00)

print(patient, procedure_1, procedure_2, procedure_3)
