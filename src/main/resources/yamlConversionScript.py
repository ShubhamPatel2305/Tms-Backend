import yaml

def reformat_data(input_file, output_file):
    with open(input_file, 'r') as infile:
        data = yaml.safe_load(infile)  # Load YAML data from file

    reformatted_data = {"locations": []}

    # Iterate through states and their districts
    for state, districts in data.items():
        state_entry = {"name": state, "districts": []}
        
        for district, district_info in districts.items():
            district_entry = {"name": district, "talukas": []}
            
            # Iterate through talukas
            for taluka, taluka_info in district_info['talukas'].items():
                taluka_entry = {"name": taluka, "cities": taluka_info['cities']}
                district_entry['talukas'].append(taluka_entry)
            
            state_entry['districts'].append(district_entry)
        
        reformatted_data['locations'].append(state_entry)

    # Write reformatted data to output file
    with open(output_file, 'w') as outfile:
        yaml.dump(reformatted_data, outfile, default_flow_style=False)

# Example file paths
input_file = 'DistrictTalukaCityMapping.yaml'  # Replace with your file
output_file = 'reformatted_file.yaml'  # Replace with your output file

# Call the function to reformat the data
reformat_data(input_file, output_file)
