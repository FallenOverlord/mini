import os
import pandas as pd
from icalendar import Calendar, Event
import datetime
from collections import defaultdict

# Function to make datetime timezone unaware and ignore the date
def normalize_time(dt):
    if isinstance(dt, datetime.datetime):
        return dt.replace(tzinfo=None).time()  # Remove timezone and extract only the time part
    return dt

# Function to extract events from a single .ics file
def extract_events(file_path):
    events = []
    with open(file_path, 'r') as file:
        calendar = Calendar.from_ical(file.read())
        for component in calendar.walk():
            if component.name == "VEVENT":
                event_summary = component.get('SUMMARY')
                event_start = normalize_time(component.get('DTSTART').dt)
                event_end = normalize_time(component.get('DTEND').dt)
                event_location = component.get('LOCATION')
                events.append({
                    "Course Name": event_summary,
                    "Start Time": event_start,
                    "End Time": event_end,
                    "Location": event_location
                })
    return events

# Function to compare timetables and find common courses
def compare_timetables(timetables):
    common_courses = defaultdict(int)
    total_courses = sum(len(timetable) for timetable in timetables)
    
    # Create a set of all unique courses from the first timetable
    reference_timetable = timetables[0]
    reference_set = set((course["Course Name"], course["Start Time"], course["End Time"], course["Location"]) for course in reference_timetable)
    
    for timetable in timetables[1:]:
        current_set = set((course["Course Name"], course["Start Time"], course["End Time"], course["Location"]) for course in timetable)
        for course in reference_set:
            if course in current_set:
                common_courses[course] += 1
    
    common_courses_set = {course for course, count in common_courses.items() if count == len(timetables) - 1}
    common_percentage = (len(common_courses_set) / total_courses) * 100
    
    return common_courses_set, common_percentage

# Function to save common courses to an Excel file
def save_to_excel(common_courses, file_path):
    df = pd.DataFrame(list(common_courses), columns=["Course Name", "Start Time", "End Time", "Location"])
    df.to_excel(file_path, index=False)
    print(f"Common courses have been successfully written to {file_path}")

# Main function to process multiple .ics files in the specified directory
def process_ics_files(directory_path, output_path):
    file_paths = [os.path.join(directory_path, file) for file in os.listdir(directory_path) if file.endswith('.ics')]
    timetables = [extract_events(file_path) for file_path in file_paths]
    
    # Debug: Print extracted events for each timetable
    for idx, timetable in enumerate(timetables):
        print(f"Timetable {idx+1}:")
        for event in timetable:
            print(event)
    
    common_courses, common_percentage = compare_timetables(timetables)
    print(f"Percentage of common courses: {common_percentage:.2f}%")
    
    # Save common courses to Excel
    save_to_excel(common_courses, output_path)

# Example usage
directory_path = 'mini\\docs\\backend\\schedules'
output_path = 'mini\\docs\\backend\\common_courses.xlsx'
process_ics_files(directory_path, output_path)
