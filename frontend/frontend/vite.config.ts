import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import dotenv from "dotenv";

// Carregar variáveis de ambiente do .env
dotenv.config();

export default defineConfig({
  plugins: [react()],
  server: {
    port: Number(process.env.VITE_PORT) || 5173, // Corrigido para `process.env`
  },
});
