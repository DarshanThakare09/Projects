import os
import pandas as pd
from PIL import Image
import torch
import torch.nn as nn
import torch.optim as optim
from torchvision import transforms
from torchvision.utils import save_image

# ================================
# Step 1: Load Dataset and Preprocess
# ================================
transform = transforms.Compose([
    transforms.Resize((64, 64)),
    transforms.ToTensor(),
    transforms.Normalize([0.5], [0.5])  # Normalize to [-1, 1]
])

def load_image(filepath):
    img = Image.open(filepath).convert("RGB")
    return transform(img)

class Dataset(torch.utils.data.Dataset):
    def __init__(self, csv_file, img_dir, transform=None):
        self.data = pd.read_csv(csv_file)
        self.img_dir = img_dir
        self.transform = transform

    def __len__(self):
        return len(self.data)

    def __getitem__(self, idx):
        row = self.data.iloc[idx]
        img_path = os.path.join(self.img_dir, row['filename'])
        image = load_image(img_path)
        description = row['description']
        return image, description

# ================================
# Step 2: Define Models
# ================================
class TextEncoder(nn.Module):
    def __init__(self, vocab_size, embed_size):
        super(TextEncoder, self).__init__()
        self.embedding = nn.Embedding(vocab_size, embed_size)
        self.fc = nn.Linear(embed_size, 128)

    def forward(self, x):
        x = self.embedding(x)
        x = x.mean(dim=1)  # Aggregate embeddings over words
        x = self.fc(x)
        return x

class Generator(nn.Module):
    def __init__(self, noise_dim, text_dim, img_channels):
        super(Generator, self).__init__()
        self.fc = nn.Sequential(
            nn.Linear(noise_dim + text_dim, 1024),
            nn.ReLU(),
            nn.Linear(1024, img_channels * 64 * 64),
            nn.Tanh()
        )

    def forward(self, noise, text_embedding):
        x = torch.cat((noise, text_embedding), dim=1)
        x = self.fc(x)
        return x.view(-1, 3, 64, 64)

class Discriminator(nn.Module):
    def __init__(self, img_channels, text_dim):
        super(Discriminator, self).__init__()
        self.fc = nn.Sequential(
            nn.Linear(img_channels * 64 * 64 + text_dim, 512),
            nn.LeakyReLU(0.2),
            nn.Linear(512, 1),
            nn.Sigmoid()
        )

    def forward(self, image, text_embedding):
        x = torch.cat((image.view(image.size(0), -1), text_embedding), dim=1)
        return self.fc(x)

# ================================
# Step 3: Training Loop
# ================================
# ================================
# Step 3: Training Loop (Updated)
# ================================
# ================================
# Step 3: Training Loop (Updated with retain_graph=True)
# ================================
def train(dataset, generator, discriminator, text_encoder, epochs=10):
    data_loader = torch.utils.data.DataLoader(dataset, batch_size=1, shuffle=True)

    # Optimizers
    optimizer_gen = optim.Adam(generator.parameters(), lr=0.0002)
    optimizer_disc = optim.Adam(discriminator.parameters(), lr=0.0002)

    # Loss function
    criterion = nn.BCELoss()

    for epoch in range(epochs):
        for image, description in data_loader:
            # Tokenize and encode description text
            description_tokens = torch.tensor([[1, 2, 3]])  # Placeholder for actual tokenization
            text_embedding = text_encoder(description_tokens)

            # Labels
            real_labels = torch.ones(1, 1)
            fake_labels = torch.zeros(1, 1)

            # Train Discriminator
            noise = torch.randn(1, 100)
            fake_image = generator(noise, text_embedding)

            optimizer_disc.zero_grad()

            # Compute loss for real and fake images
            real_output = discriminator(image, text_embedding)
            loss_real = criterion(real_output, real_labels)

            fake_output = discriminator(fake_image.detach(), text_embedding)  # Detach generator output for discriminator
            loss_fake = criterion(fake_output, fake_labels)

            # Total discriminator loss
            loss_disc = loss_real + loss_fake

            loss_disc.backward(retain_graph=True)  # Backpropagate for the discriminator with retain_graph=True
            optimizer_disc.step()

            # Train Generator (only once per iteration)
            optimizer_gen.zero_grad()

            # Here, we only compute the generator's loss from the discriminator's output
            fake_output = discriminator(fake_image, text_embedding)
            loss_gen = criterion(fake_output, real_labels)  # Generator wants discriminator to think it's real

            loss_gen.backward()  # Backpropagate for the generator
            optimizer_gen.step()

        print(f"Epoch [{epoch+1}/{epochs}], Loss D: {loss_disc.item():.4f}, Loss G: {loss_gen.item():.4f}")

# ================================
# Step 4: Generate New Images
# ================================
def generate_image(generator, text_encoder, description):
    noise = torch.randn(1, 100)
    description_tokens = torch.tensor([[1, 2, 3]])  # Placeholder for actual tokenization
    text_embedding = text_encoder(description_tokens)

    generated_image = generator(noise, text_embedding)
    save_image(generated_image, "generated_image.png")
    print("Generated image saved!")

# ================================
# Step 5: Run the Code
# ================================
if __name__ == "__main__":
    # Load dataset
    dataset = Dataset(csv_file="data.csv", img_dir="images", transform=transform)

    # Initialize models
    generator = Generator(noise_dim=100, text_dim=128, img_channels=3)
    discriminator = Discriminator(img_channels=3, text_dim=128)
    text_encoder = TextEncoder(vocab_size=5000, embed_size=256)

    # Train models
    train(dataset, generator, discriminator, text_encoder, epochs=5)

    # Generate and save a new image
    generate_image(generator, text_encoder, "A bird drinking water from a pond")
