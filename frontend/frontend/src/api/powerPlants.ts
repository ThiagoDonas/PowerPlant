import api from "./api";

export interface PowerPlant {
  id: number;
  codeCEG: string;
  name: string;
  state: string;
  energySource: string;
  generationType: string;
  capacity: number;
  status: string;
  connectionType: string;
  connectionCompany: string;
  connectionVoltage: number;
  reportDate: string;
}

export const fetchTop5PowerPlants = async (): Promise<PowerPlant[]> => {
  try {
    const response = await api.get<PowerPlant[]>("power_plants/top5");
    console.log(response)
    return response.data;
  } catch (error) {
    console.error("⚠️ Failed to fetch top 5 power plants:", error);
    return [];
  }
};


