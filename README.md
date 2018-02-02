Base URI: `http://localhost:9090`

#### __`POST`__ `/api/getKurikulum/{kode_kurikulum}`
Mendapatkan kurikulum jika diberikan kode kurikulum.


**Request Parameter:**

Form:
* **kode_kurikulum**, kode kurikulum yang ingin didapatkan

**Response:**

Contoh respon sukses
```json
{
"kode":"15CSI001",
"nama":"Kurikulum Ilmu Komputer 1",
"tahun":2015,
"sksWajib":37,
"sksPilihan":17,
"matkulList":[
{
"kode":"CSI00001",
"nama":"Desktop Support Technician",
"jumlahSKS":1,
"deskripsi":null,
"sksPrasyarat":0,
"isWajib":0,
"term":1
},
{
"kode":"CSI00051",
"nama":"Sales Representative",
"jumlahSKS":4,
"deskripsi":null,
"sksPrasyarat":0,
"isWajib":0,
"term":1
}
]
}
```

Contoh respon gagal
```json
{}
```

---
#### __`GET`__ `/api/kurikulum/viewall`
Mendapatkan list kurikulum.

**Response:**

Contoh respon sukses:
```json
[
{
"kode":"15CSI001",
"nama":"Kurikulum Ilmu Komputer 1",
"tahun":2015,
"sksWajib":37,
"sksPilihan":17,
"matkulList":null
},
{
"kode":"15CSI002",
"nama":"Kurikulum Ilmu Komputer 2",
"tahun":2015,
"sksWajib":39,
"sksPilihan":25,
"matkulList":null
}
]
```

Contoh respon gagal
```json
{}
```

---
#### __`GET`__ `/api/getKurikulum/prodi/{kode_univ}/{kode_fak}/{kode_prodi}`
Mendapatkan kurikulum berdasarkan prodi.


**Request Parameter:**

Query String:
* **kode_univ**, kode universitas
* **kode_fak**, kode fakultas
* **kode_prodi**, kode prodi


Contoh request:
* `/api/getKurikulum/prodi/1/1/1`

**Response:**

Contoh respon sukses:
```json
[
{
"kode":"15CSI001",
"nama":"Kurikulum Ilmu Komputer 1",
"tahun":2015,
"sksWajib":37,
"sksPilihan":17,
"matkulList":null
},
{
"kode":"15CSI028",
"nama":"Kurikulum Ilmu Komputer 28",
"tahun":2015,
"sksWajib":39,
"sksPilihan":24,
"matkulList":null
}
]
```

Contoh respon gagal
```json
{}
```

---
#### __`GET`__ `/api/getMataKuliah/kurikulum/{kode}`
Mendapatkan mata kuliah berdasarkan kurikulum.


**Request Parameter:**

Query String:
* **kode**, kode kurikulum


Contoh request:
* `/api/getMataKuliah/kurikulum/15CSI001`

**Response:**

Contoh respon sukses:
```json
[
{
"kode":"CSI00001",
"nama":"Desktop Support Technician",
"jumlahSKS":1,
"deskripsi":null,
"sksPrasyarat":0,
"isWajib":0,
"term":1
},
{
"kode":"CSI00051",
"nama":"Sales Representative",
"jumlahSKS":4,
"deskripsi":null,
"sksPrasyarat":0,
"isWajib":0,
"term":1
}
]
```

Contoh respon gagal
```json
{}
```

---
#### __`GET`__ `/api/getMataKuliah/{kode}`
Mendapatkan mata kuliah berdasarkan kode mata kuliah.


**Request Parameter:**

Query String:
* **kode**, kode mata kuliah


Contoh request:
* `/api/getMataKuliah/CSI00001`

**Response:**

Contoh respon sukses:
```json
{
"kode":"CSI00001",
"nama":"Desktop Support Technician",
"jumlahSKS":1,
"deskripsi":null,
"sksPrasyarat":null,
"isWajib":null,
"term":null
}
```

Contoh respon gagal
```json
{}
```

---
#### __`GET`__ `/api/matkul/viewall`
Mendapatkan list mata kuliah.

**Response:**

Contoh respon sukses:
```json
[
{
"kode":"CSI00001",
"nama":"Desktop Support Technician",
"jumlahSKS":1,
"deskripsi":null,
"sksPrasyarat":null,
"isWajib":null,
"term":null
},
{
"kode":"CSI00002",
"nama":"Recruiter",
"jumlahSKS":3,
"deskripsi":null,
"sksPrasyarat":null,
"isWajib":null,
"term":null
}
]
```

Contoh respon gagal
```json
{}
```

---
#### __`GET`__ `/api/getPrasyarat/{kode_kurikulum}/{kode_matkul}`
Mendapatkan prasyarat berdasarkan kode kurikulum dan kode mata kuliah.


**Request Parameter:**

Query String:
* **kode_kurikulum**, kode kurikulum
* **kode_matkul**, kode mata kuliah


Contoh request:
* `/api/getPrasyarat/15CSI001/CSI00001`

**Response:**

Contoh respon sukses:
```json
[
{"kode":"CSI00701",
"nama":"Help Desk Operator",
"jumlahSKS":4,
"deskripsi":null,
"sksPrasyarat":null,
"isWajib":null,"term":null
},
{"kode":"CSI00751",
"nama":"Compensation Analyst",
"jumlahSKS":4,
"deskripsi":null,
"sksPrasyarat":null,
"isWajib":null,
"term":null
}
]
```

Contoh respon gagal
```json
{}
```
