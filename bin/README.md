Base URI: `http://localhost:1234`

#### __`POST`__ `/api/kurikulum/getKurikulum`
Mendapatkan kurikulum jika diberikan ID kurikulum.


**Request Parameter:**

Form:
* **id**, id kurikulum yang ingin didapatkan

**Response:**

Contoh respon sukses
```json
{
	"id_kurikulum": "123",
    "mata_kuliah": [
    	{ 
        	"judul": "APAP",
            "sks": "3",
            "term": "5",
            "prasyarat": [
            	{
                	"judul":"DDP",
                    "sks":"6",
                    "term":"1",
                    "prasyarat": []
                },
                ...
            ]
        },
        ...
    ]
}
```

Contoh respon gagal
```json
{}
```

---
#### __`GET`__ `/api/kurikulum/getKurikulum/:id`
Mendapatkan kurikulum jika diberikan ID kurikulum.


**Request Parameter:**

Path Variable:
* **id**, id kurikulum yang ingin didapatkan

Contoh request:
* `/api/kurikulum/getKurikulum/123`
* `/api/kurikulum/getKurikulum/456`

**Response:**

Contoh respon sukses:
```json
{
	"id_kurikulum": "123",
    "mata_kuliah": [
    	{ 
        	"judul": "APAP",
            "sks": "3",
            "term": "5",
            "prasyarat": [
            	{
                	"judul":"DDP",
                    "sks":"6",
                    "term":"1",
                    "prasyarat": []
                },
                ...
            ]
        },
        ...
    ]
}
```

Contoh respon gagal
```json
{}
```

---
#### __`GET`__ `/api/kurikulum/getKurikulum`
Mendapatkan kurikulum jika diberikan ID kurikulum.


**Request Parameter:**

Query String:
* **id**, id kurikulum yang ingin didapatkan

Contoh request:
* `/api/kurikulum/getKurikulum?id=123`
* `/api/kurikulum/getKurikulum?id=456`

**Response:**

Contho respon sukses:
```json
{
	"id_kurikulum": "123",
    "mata_kuliah": [
    	{ 
        	"judul": "APAP",
            "sks": "3",
            "term": "5",
            "prasyarat": [
            	{
                	"judul":"DDP",
                    "sks":"6",
                    "term":"1",
                    "prasyarat": []
                },
                ...
            ]
        },
        ...
    ]
}
```

Contoh respon gagal
```json
{}
```
